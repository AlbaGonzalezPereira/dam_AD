(: Contar el número de profesores que hay en un departamento dado.. Solución: para el departamento Psicología :)
count(for $x in doc("universidad.xml")//departamento
where $x/@id="D001"
return $x//profesor),

(:Media de antigüedad de los profesores de la facultad de Ingeniería que trabajan algún Lunes. Solución: 7.666…:)
avg(for $x in doc("universidad.xml")//facultad[@nombre="Ingeniería"]//profesor
where $x/horario[@dia="Lunes"]
return $x/@antigüedad),

(:Número medio de cursos ofrecidos por cada facultad. Solución: 3.66…:)
round(avg(for $x in doc("universidad.xml")//facultad
return count($x//curso)),2)