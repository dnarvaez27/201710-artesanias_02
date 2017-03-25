resources=(feria boleta artesano artesania stand pabellon ciudad espacio organizador usuario conferencia salon review cliente)
for r in ${resources[*]}
do
    mkdir "%s" $r
    touch "$r/.keep"
done
