PGDMP  .                    |            postgres    17.2    17.0     l           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            m           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            n           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            o           1262    5    postgres    DATABASE     s   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE postgres;
                     postgres    false            p           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                        postgres    false    3439                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            q           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    36179    accounts    TABLE     �   CREATE TABLE public.accounts (
    account_number integer,
    balance real,
    state boolean,
    customer_id bigint,
    id bigint NOT NULL,
    type character varying(255)
);
    DROP TABLE public.accounts;
       public         heap r       postgres    false    4            �            1259    36178    accounts_id_seq    SEQUENCE     �   ALTER TABLE public.accounts ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.accounts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    4    218            �            1259    36185 	   customers    TABLE     �   CREATE TABLE public.customers (
    state boolean,
    id bigint NOT NULL,
    person_id bigint,
    client_id character varying(255),
    password character varying(255)
);
    DROP TABLE public.customers;
       public         heap r       postgres    false    4            �            1259    36184    customers_id_seq    SEQUENCE     �   ALTER TABLE public.customers ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    4    220            �            1259    36195    persons    TABLE       CREATE TABLE public.persons (
    age integer,
    dni integer,
    phone_number integer,
    id bigint NOT NULL,
    address character varying(255),
    first_name character varying(255),
    gender character varying(255),
    last_name character varying(255)
);
    DROP TABLE public.persons;
       public         heap r       postgres    false    4            �            1259    36194    persons_id_seq    SEQUENCE     �   ALTER TABLE public.persons ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.persons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    222    4            �            1259    36203    transactions    TABLE     �   CREATE TABLE public.transactions (
    last_balance real,
    transaction_balance real,
    account_id bigint,
    id bigint NOT NULL
);
     DROP TABLE public.transactions;
       public         heap r       postgres    false    4            �            1259    36202    transactions_id_seq    SEQUENCE     �   ALTER TABLE public.transactions ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.transactions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    224    4            c          0    36179    accounts 
   TABLE DATA           Y   COPY public.accounts (account_number, balance, state, customer_id, id, type) FROM stdin;
    public               postgres    false    218   4#       e          0    36185 	   customers 
   TABLE DATA           N   COPY public.customers (state, id, person_id, client_id, password) FROM stdin;
    public               postgres    false    220   �#       g          0    36195    persons 
   TABLE DATA           e   COPY public.persons (age, dni, phone_number, id, address, first_name, gender, last_name) FROM stdin;
    public               postgres    false    222   3$       i          0    36203    transactions 
   TABLE DATA           Y   COPY public.transactions (last_balance, transaction_balance, account_id, id) FROM stdin;
    public               postgres    false    224   %       r           0    0    accounts_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.accounts_id_seq', 10, true);
          public               postgres    false    217            s           0    0    customers_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.customers_id_seq', 5, true);
          public               postgres    false    219            t           0    0    persons_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.persons_id_seq', 5, true);
          public               postgres    false    221            u           0    0    transactions_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.transactions_id_seq', 2, true);
          public               postgres    false    223            �           2606    36183    accounts accounts_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.accounts DROP CONSTRAINT accounts_pkey;
       public                 postgres    false    218            �           2606    36193 !   customers customers_person_id_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_person_id_key UNIQUE (person_id);
 K   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_person_id_key;
       public                 postgres    false    220            �           2606    36191    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public                 postgres    false    220            �           2606    36201    persons persons_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.persons DROP CONSTRAINT persons_pkey;
       public                 postgres    false    222            �           2606    36207    transactions transactions_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_pkey;
       public                 postgres    false    224            �           2606    36218 (   transactions fk20w7wsg13u9srbq3bd7chfxdh    FK CONSTRAINT     �   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT fk20w7wsg13u9srbq3bd7chfxdh FOREIGN KEY (account_id) REFERENCES public.accounts(id);
 R   ALTER TABLE ONLY public.transactions DROP CONSTRAINT fk20w7wsg13u9srbq3bd7chfxdh;
       public               postgres    false    224    3269    218            �           2606    36213 %   customers fk2wkt5i862ljgauvs6mtjh8d8v    FK CONSTRAINT     �   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT fk2wkt5i862ljgauvs6mtjh8d8v FOREIGN KEY (person_id) REFERENCES public.persons(id);
 O   ALTER TABLE ONLY public.customers DROP CONSTRAINT fk2wkt5i862ljgauvs6mtjh8d8v;
       public               postgres    false    3275    220    222            �           2606    36208 $   accounts fkn6x8pdp50os8bq5rbb792upse    FK CONSTRAINT     �   ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT fkn6x8pdp50os8bq5rbb792upse FOREIGN KEY (customer_id) REFERENCES public.customers(id);
 N   ALTER TABLE ONLY public.accounts DROP CONSTRAINT fkn6x8pdp50os8bq5rbb792upse;
       public               postgres    false    218    220    3273            c   �   x�M�;
BA����bd�Nj�`k#"*���~�����|��gq@z���q����{RJ���.�m/.r����`%��"�%���`s���EK�Ex ��W
!G1�����S�I�mGƇ����Z�|O9+      e   Z   x��=
�0@�99�4i�7Z���P��?�|��M �r==��q�z�m��rq�ּ�J�qúR�&�G�ú�XF5Ss������7��      g   �   x�E�;N1��Spd���	�$�!I����y���g�b���^R�GGV| B��FN!�ᱎ:��?k;`��ץ��%8	����,:3��Y�������(Nǫ����b�t�{9�ػ��l�ձ̊>�z��t*��o�l
L�gAN�Y��`<,�b��?w_�s5ܭ���Ĕ��Ar������a)���u������I�      i      x�340��52�4�4䲀1��b���� :��     