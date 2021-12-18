ALTER TABLE client
    ADD
        COLUMN address_id bigint
        constraint fk_address references address (id);

ALTER TABLE client
    ADD
        COLUMN phone_id bigint
        constraint fk_phone references phone (id);
