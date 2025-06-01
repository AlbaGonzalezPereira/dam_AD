for $x in doc("universidad.xml")//departamento where $x[@nombre="Historia del Arte"]
return count($x//profesor),


avg(for $p in doc("universidad.xml")//facultad[@nombre="Ingeniería"]//profesor 
let $h := for $pr in $p
              where $pr/horario[@dia="Lunes"]
              return xs:decimal($pr/@antigüedad)
return $h),

avg(for $x in doc("universidad.xml")//facultad
return count($x//curso))