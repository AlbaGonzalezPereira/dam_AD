use biblioteca
db.autores.insertMany([
    {
      "_id": 1,
      "nombre": "Gabriel García Márquez",
      "edad": 87,
      "nacionalidad": "Colombiana",
      "genero": "Realismo Mágico",
      "libros": [
        {
          "titulo": "Cien años de soledad",
          "fecha_publicacion": "1967",
          "genero": "Realismo Mágico",
          "ventas": 30000000
        },
        {
          "titulo": "El amor en los tiempos del cólera",
          "fecha_publicacion": "1985",
          "genero": "Realismo Mágico",
          "ventas": 15000000
        }
      ],
      "premios": [
        {
          "nombre": "Premio Nobel de Literatura",
          "anio": 1982
        },
        {
          "nombre": "Premio Rómulo Gallegos",
          "anio": 1972
        }
      ]
    },
    {
      "_id": 2,
      "nombre": "J.K. Rowling",
      "edad": 58,
      "nacionalidad": "Británica",
      "genero": "Fantasía",
      "libros": [
        {
          "titulo": "Harry Potter y la piedra filosofal",
          "fecha_publicacion": "1997",
          "genero": "Fantasía",
          "ventas": 120000000
        },
        {
          "titulo": "Harry Potter y la cámara secreta",
          "fecha_publicacion": "1998",
          "genero": "Fantasía",
          "ventas": 95000000
        }
      ],
      "premios": [
        {
          "nombre": "Príncipe de Asturias de la Concordia",
          "anio": 2003
        },
        {
          "nombre": "Premio Hugo",
          "anio": 2001
        }
      ]
    },
    {
      "_id": 3,
      "nombre": "Haruki Murakami",
      "edad": 74,
      "nacionalidad": "Japonesa",
      "genero": "Realismo Mágico / Ficción contemporánea",
      "libros": [
        {
          "titulo": "Norwegian Wood",
          "fecha_publicacion": "1987",
          "genero": "Realismo Mágico",
          "ventas": 10000000
        },
        {
          "titulo": "Kafka en la orilla",
          "fecha_publicacion": "2002",
          "genero": "Ficción contemporánea",
          "ventas": 8000000
        }
      ],
      "premios": [
        {
          "nombre": "Premio Franz Kafka",
          "anio": 2006
        },
        {
          "nombre": "Premio Internacional de Dublín para la Literatura",
          "anio": 2011
        }
      ]
    },
    {
      "_id": 4,
      "nombre": "Isabel Allende",
      "edad": 82,
      "nacionalidad": "Chilena",
      "genero": "Ficción histórica",
      "libros": [
        {
          "titulo": "La casa de los espíritus",
          "fecha_publicacion": "1982",
          "genero": "Ficción histórica",
          "ventas": 20000000
        },
        {
          "titulo": "Eva Luna",
          "fecha_publicacion": "1987",
          "genero": "Ficción histórica",
          "ventas": 8000000
        }
      ],
      "premios": [
        {
          "nombre": "Premio Nacional de Literatura",
          "anio": 2010
        },
        {
          "nombre": "Premio Gabriela Mistral",
          "anio": 2014
        }
      ]
    },
    {
      "_id": 5,
      "nombre": "Haruki Murakami",
      "nacionalidad": "Japonesa",
      "fecha_nacimiento": "1949-01-12",
      "genero": "Ficción Literaria",
      "libros": [
        {
          "titulo": "Norwegian Wood",
          "anio_publicacion": 1987,
          "ventas": 10000000
        },
        {
          "titulo": "Kafka en la orilla",
          "anio_publicacion": 2002,
          "ventas": 8000000
        }
      ],
      "premios": []
    }
])
