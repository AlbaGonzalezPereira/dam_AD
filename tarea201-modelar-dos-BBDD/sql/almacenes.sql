PGDMP                  
    |            almacen    16.2    16.2 (    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16693    almacen    DATABASE     z   CREATE DATABASE almacen WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE almacen;
                postgres    false            ]           1247    16722    contacto    TYPE     �   CREATE TYPE public.contacto AS (
	nombre_contacto character varying(50),
	telefono character varying(15),
	email character varying(30)
);
    DROP TYPE public.contacto;
       public          postgres    false            �            1259    16695 	   almacenes    TABLE     �   CREATE TABLE public.almacenes (
    id_almacen integer NOT NULL,
    nombre_almacen character varying(50),
    ubicacion character varying(50)
);
    DROP TABLE public.almacenes;
       public         heap    postgres    false            �            1259    16694    almacenes_id_almacen_seq    SEQUENCE     �   CREATE SEQUENCE public.almacenes_id_almacen_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.almacenes_id_almacen_seq;
       public          postgres    false    216            �           0    0    almacenes_id_almacen_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.almacenes_id_almacen_seq OWNED BY public.almacenes.id_almacen;
          public          postgres    false    215            �            1259    16749    almacenes_productos    TABLE     �   CREATE TABLE public.almacenes_productos (
    id_almacen integer NOT NULL,
    id_producto integer NOT NULL,
    cantidad integer
);
 '   DROP TABLE public.almacenes_productos;
       public         heap    postgres    false            �            1259    16709 
   categorias    TABLE     n   CREATE TABLE public.categorias (
    id_categoria integer NOT NULL,
    nombre_categoria character varying
);
    DROP TABLE public.categorias;
       public         heap    postgres    false            �            1259    16708    categorias_id_categoria_seq    SEQUENCE     �   CREATE SEQUENCE public.categorias_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.categorias_id_categoria_seq;
       public          postgres    false    219            �           0    0    categorias_id_categoria_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.categorias_id_categoria_seq OWNED BY public.categorias.id_categoria;
          public          postgres    false    218            �            1259    16702 	   productos    TABLE     x   CREATE TABLE public.productos (
    id_producto integer NOT NULL,
    id_proveedor integer,
    id_categoria integer
);
    DROP TABLE public.productos;
       public         heap    postgres    false            �            1259    16732    productos_id_producto_seq    SEQUENCE     �   CREATE SEQUENCE public.productos_id_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.productos_id_producto_seq;
       public          postgres    false    217            �           0    0    productos_id_producto_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.productos_id_producto_seq OWNED BY public.productos.id_producto;
          public          postgres    false    223            �            1259    16717    proveedores    TABLE     �   CREATE TABLE public.proveedores (
    id_proveedor integer NOT NULL,
    nombre_proveedor character varying(50),
    nif character varying(10),
    contacto public.contacto
);
    DROP TABLE public.proveedores;
       public         heap    postgres    false    861            �            1259    16723    proveedores_id_proveedor_seq    SEQUENCE     �   CREATE SEQUENCE public.proveedores_id_proveedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.proveedores_id_proveedor_seq;
       public          postgres    false    220            �           0    0    proveedores_id_proveedor_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.proveedores_id_proveedor_seq OWNED BY public.proveedores.id_proveedor;
          public          postgres    false    222            1           2604    16698    almacenes id_almacen    DEFAULT     |   ALTER TABLE ONLY public.almacenes ALTER COLUMN id_almacen SET DEFAULT nextval('public.almacenes_id_almacen_seq'::regclass);
 C   ALTER TABLE public.almacenes ALTER COLUMN id_almacen DROP DEFAULT;
       public          postgres    false    216    215    216            3           2604    16712    categorias id_categoria    DEFAULT     �   ALTER TABLE ONLY public.categorias ALTER COLUMN id_categoria SET DEFAULT nextval('public.categorias_id_categoria_seq'::regclass);
 F   ALTER TABLE public.categorias ALTER COLUMN id_categoria DROP DEFAULT;
       public          postgres    false    219    218    219            2           2604    16733    productos id_producto    DEFAULT     ~   ALTER TABLE ONLY public.productos ALTER COLUMN id_producto SET DEFAULT nextval('public.productos_id_producto_seq'::regclass);
 D   ALTER TABLE public.productos ALTER COLUMN id_producto DROP DEFAULT;
       public          postgres    false    223    217            4           2604    16724    proveedores id_proveedor    DEFAULT     �   ALTER TABLE ONLY public.proveedores ALTER COLUMN id_proveedor SET DEFAULT nextval('public.proveedores_id_proveedor_seq'::regclass);
 G   ALTER TABLE public.proveedores ALTER COLUMN id_proveedor DROP DEFAULT;
       public          postgres    false    222    220            �          0    16695 	   almacenes 
   TABLE DATA           J   COPY public.almacenes (id_almacen, nombre_almacen, ubicacion) FROM stdin;
    public          postgres    false    216   �.       �          0    16749    almacenes_productos 
   TABLE DATA           P   COPY public.almacenes_productos (id_almacen, id_producto, cantidad) FROM stdin;
    public          postgres    false    224   �.       �          0    16709 
   categorias 
   TABLE DATA           D   COPY public.categorias (id_categoria, nombre_categoria) FROM stdin;
    public          postgres    false    219   /       �          0    16702 	   productos 
   TABLE DATA           L   COPY public.productos (id_producto, id_proveedor, id_categoria) FROM stdin;
    public          postgres    false    217   @/       �          0    16717    proveedores 
   TABLE DATA           T   COPY public.proveedores (id_proveedor, nombre_proveedor, nif, contacto) FROM stdin;
    public          postgres    false    220   j/       �           0    0    almacenes_id_almacen_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.almacenes_id_almacen_seq', 4, true);
          public          postgres    false    215            �           0    0    categorias_id_categoria_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.categorias_id_categoria_seq', 9, true);
          public          postgres    false    218            �           0    0    productos_id_producto_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.productos_id_producto_seq', 6, true);
          public          postgres    false    223            �           0    0    proveedores_id_proveedor_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.proveedores_id_proveedor_seq', 8, true);
          public          postgres    false    222            >           2606    16753 '   almacenes_productos PK_almacen_producto 
   CONSTRAINT     |   ALTER TABLE ONLY public.almacenes_productos
    ADD CONSTRAINT "PK_almacen_producto" PRIMARY KEY (id_almacen, id_producto);
 S   ALTER TABLE ONLY public.almacenes_productos DROP CONSTRAINT "PK_almacen_producto";
       public            postgres    false    224    224            8           2606    16738    productos PK_producto 
   CONSTRAINT     ^   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT "PK_producto" PRIMARY KEY (id_producto);
 A   ALTER TABLE ONLY public.productos DROP CONSTRAINT "PK_producto";
       public            postgres    false    217            6           2606    16700    almacenes almacenes_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.almacenes
    ADD CONSTRAINT almacenes_pkey PRIMARY KEY (id_almacen);
 B   ALTER TABLE ONLY public.almacenes DROP CONSTRAINT almacenes_pkey;
       public            postgres    false    216            :           2606    16716    categorias categorias_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT categorias_pkey PRIMARY KEY (id_categoria);
 D   ALTER TABLE ONLY public.categorias DROP CONSTRAINT categorias_pkey;
       public            postgres    false    219            <           2606    16731    proveedores proveedores_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.proveedores
    ADD CONSTRAINT proveedores_pkey PRIMARY KEY (id_proveedor);
 F   ALTER TABLE ONLY public.proveedores DROP CONSTRAINT proveedores_pkey;
       public            postgres    false    220            A           2606    16754    almacenes_productos FK_almacen    FK CONSTRAINT     �   ALTER TABLE ONLY public.almacenes_productos
    ADD CONSTRAINT "FK_almacen" FOREIGN KEY (id_almacen) REFERENCES public.almacenes(id_almacen) NOT VALID;
 J   ALTER TABLE ONLY public.almacenes_productos DROP CONSTRAINT "FK_almacen";
       public          postgres    false    224    4662    216            B           2606    16759    almacenes_productos FK_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.almacenes_productos
    ADD CONSTRAINT "FK_producto" FOREIGN KEY (id_producto) REFERENCES public.productos(id_producto) NOT VALID;
 K   ALTER TABLE ONLY public.almacenes_productos DROP CONSTRAINT "FK_producto";
       public          postgres    false    224    4664    217            ?           2606    16744    productos FK_producto_categoria    FK CONSTRAINT     �   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT "FK_producto_categoria" FOREIGN KEY (id_categoria) REFERENCES public.categorias(id_categoria) NOT VALID;
 K   ALTER TABLE ONLY public.productos DROP CONSTRAINT "FK_producto_categoria";
       public          postgres    false    219    217    4666            @           2606    24885    productos FK_producto_proveedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT "FK_producto_proveedor" FOREIGN KEY (id_proveedor) REFERENCES public.proveedores(id_proveedor) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 K   ALTER TABLE ONLY public.productos DROP CONSTRAINT "FK_producto_proveedor";
       public          postgres    false    220    4668    217            �   =   x�3�L��MLN�K-Vp�ϫ:�0'��3,3=��I������y%�e�)E�\1z\\\ ��      �      x�3�4�4�2��4�2�4�c���� #�      �   %   x����*M/M-I-:�6�˒� �891���� Ҫ�      �      x�3�4��2�@Ғ+F��� $u�      �   `   x�3��*M/M-I-:�6Q� � ���������SC���������P$吞���������e�Y�Z���јU���	W��	3�\$��3F��� ��#8     