package ru.otus.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WebServerWithFilterBasedSecurityDemo;
import ru.otus.dao.crm.model.Client;
import ru.otus.dao.crm.model.User;
import ru.otus.dao.crm.services.TemplateProcessor;
import ru.otus.dao.crm.services.client.DBServiceClient;
import ru.otus.dao.crm.services.client.DBServiceClientImpl;
import ru.otus.dao.crm.services.user.UserAuthService;

import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static ru.otus.server.utils.WebServerHelper.COOKIE_HEADER;
import static ru.otus.server.utils.WebServerHelper.buildUrl;
import static ru.otus.server.utils.WebServerHelper.login;

@DisplayName("Тест сервера должен ")
class ClientsWebServerImplTest {

    private static final int WEB_SERVER_PORT = 8989;
    private static final String WEB_SERVER_URL = "http://localhost:" + WEB_SERVER_PORT + "/";
    private static final String LOGIN_URL = "login";
    private static final String API_CLIENT_URL = "api/clients";

    private static final long DEFAULT_USER_ID = 2L;
    private static final String DEFAULT_USER_LOGIN = "admin";
    private static final String DEFAULT_USER_ROLE = "ADMIN";
    private static final String DEFAULT_USER_PASSWORD = "admin";


    private static final long DEFAULT_CLIENT_ID = 2L;
    private static final String DEFAULT_CLIENT_NAME = "artem";
    private static final String DEFAULT_CLIENT_PHONE_NUMBER = "8987213";
    private static final String DEFAULT_CLIENT_ADDRESS = "default address";


    private static final Client DEFAULT_CLIENT = new Client(DEFAULT_CLIENT_ID, DEFAULT_CLIENT_NAME);
    private static final String INCORRECT_USER_LOGIN = "BadUser";

    private static Gson gson;
    private static ClientsWebServer webServer;
    private static HttpClient http;

    @BeforeAll
    static void setUp() throws Exception {
        http = HttpClient.newHttpClient();

        TemplateProcessor templateProcessor = mock(TemplateProcessor.class);
        DBServiceClient clientService = mock(DBServiceClient.class);
        UserAuthService userAuthService = mock(UserAuthService.class);

        given(userAuthService.authenticate(DEFAULT_USER_LOGIN, DEFAULT_USER_PASSWORD)).willReturn(true);
        given(userAuthService.authenticate(INCORRECT_USER_LOGIN, DEFAULT_USER_PASSWORD)).willReturn(false);
        given(clientService.findById(DEFAULT_USER_ID)).willReturn(Optional.of(DEFAULT_CLIENT));

        gson = new GsonBuilder().serializeNulls().create();
        webServer = new ClientsWebServerWithFilterBasedSecurity(WEB_SERVER_PORT, userAuthService, clientService, gson, templateProcessor);
        webServer.start();
    }

    @AfterAll
    static void tearDown() throws Exception {
        webServer.stop();
    }

    @DisplayName("возвращать 302 при запросе пользователя по id если не выполнен вход ")
    @Test
    void shouldReturnForbiddenStatusForUserRequestWhenUnauthorized() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create(buildUrl(WEB_SERVER_URL, API_CLIENT_URL)))
                .build();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_MOVED_TEMP);
    }

    @DisplayName("возвращать ID сессии при выполнении входа с верными данными")
    @Test
    void shouldReturnJSessionIdWhenLoggingInWithCorrectData() throws Exception {
        HttpCookie jSessionIdCookie = login(buildUrl(WEB_SERVER_URL, LOGIN_URL), DEFAULT_USER_LOGIN, DEFAULT_USER_PASSWORD);
        assertThat(jSessionIdCookie).isNotNull();
    }

    @DisplayName("не возвращать ID сессии при выполнении входа если данные входа не верны")
    @Test
    void shouldNotReturnJSessionIdWhenLoggingInWithIncorrectData() throws Exception {
        HttpCookie jSessionIdCookie = login(buildUrl(WEB_SERVER_URL, LOGIN_URL), INCORRECT_USER_LOGIN, DEFAULT_USER_PASSWORD);
        assertThat(jSessionIdCookie).isNull();
    }

    @DisplayName("возвращать корректные данные при запросе пользователя по id если вход выполнен")
    @Test
    void shouldReturnCorrectUserWhenAuthorized() throws Exception {
        HttpCookie jSessionIdCookie = login(buildUrl(WEB_SERVER_URL, LOGIN_URL), DEFAULT_USER_LOGIN, DEFAULT_USER_PASSWORD);
        assertThat(jSessionIdCookie).isNotNull();

        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create(buildUrl(WEB_SERVER_URL, API_CLIENT_URL, String.valueOf(DEFAULT_USER_ID))))
                .setHeader(COOKIE_HEADER, String.format("%s=%s", jSessionIdCookie.getName(), jSessionIdCookie.getValue()))
                .build();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
        assertThat(response.body()).isEqualTo(gson.toJson(DEFAULT_CLIENT));
    }
}