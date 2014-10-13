(defrule grid-values
	
	?f <- (phase grid-values)
	
	=>
	
	(retract ?f)
	
	(assert (phase expand-any))
	
	(assert (size 3))

	(assert (possible (row 1) (column 1) (value any) (group 1) (diagonal 1) (id 1)))
	(assert (possible (row 1) (column 2) (value any) (group 1) (diagonal 0) (id 2)))
	(assert (possible (row 1) (column 3) (value 1)   (group 1) (diagonal 0) (id 3)))
	(assert (possible (row 2) (column 1) (value any) (group 1) (diagonal 0) (id 4)))
	(assert (possible (row 2) (column 2) (value any) (group 1) (diagonal 1) (id 5)))
	(assert (possible (row 2) (column 3) (value any) (group 1) (diagonal 0) (id 6)))

	(assert (possible (row 1) (column 4) (value any) (group 2) (diagonal 0) (id 7)))
	(assert (possible (row 1) (column 5) (value any) (group 2) (diagonal 0) (id 8)))
	(assert (possible (row 1) (column 6) (value any) (group 2) (diagonal 2) (id 9)))
	(assert (possible (row 2) (column 4) (value 1)   (group 2) (diagonal 0) (id 10)))
	(assert (possible (row 2) (column 5) (value 3)   (group 2) (diagonal 2) (id 11)))
	(assert (possible (row 2) (column 6) (value 5)   (group 2) (diagonal 0) (id 12)))

	(assert (possible (row 3) (column 1) (value any) (group 3) (diagonal 0) (id 13)))
	(assert (possible (row 3) (column 2) (value 6)   (group 3) (diagonal 0) (id 14)))
	(assert (possible (row 3) (column 3) (value any) (group 3) (diagonal 1) (id 15)))
	(assert (possible (row 4) (column 1) (value 4)   (group 3) (diagonal 0) (id 16)))
	(assert (possible (row 4) (column 2) (value any) (group 3) (diagonal 0) (id 17)))
	(assert (possible (row 4) (column 3) (value any) (group 3) (diagonal 2) (id 18)))

	(assert (possible (row 3) (column 4) (value 4)   (group 4) (diagonal 2) (id 19)))
	(assert (possible (row 3) (column 5) (value any) (group 4) (diagonal 0) (id 20)))
	(assert (possible (row 3) (column 6) (value 2)   (group 4) (diagonal 0) (id 21)))
	(assert (possible (row 4) (column 4) (value any) (group 4) (diagonal 1) (id 22)))
	(assert (possible (row 4) (column 5) (value any) (group 4) (diagonal 0) (id 23)))
	(assert (possible (row 4) (column 6) (value any) (group 4) (diagonal 0) (id 24)))

	(assert (possible (row 5) (column 1) (value 3)   (group 5) (diagonal 0) (id 25)))
	(assert (possible (row 5) (column 2) (value any) (group 5) (diagonal 2) (id 26)))
	(assert (possible (row 5) (column 3) (value any) (group 5) (diagonal 0) (id 27)))
	(assert (possible (row 6) (column 1) (value any) (group 5) (diagonal 2) (id 28)))
	(assert (possible (row 6) (column 2) (value any) (group 5) (diagonal 0) (id 29)))
	(assert (possible (row 6) (column 3) (value any) (group 5) (diagonal 0) (id 30)))

	(assert (possible (row 5) (column 4) (value any) (group 6) (diagonal 0) (id 31)))
	(assert (possible (row 5) (column 5) (value any) (group 6) (diagonal 1) (id 32)))
	(assert (possible (row 5) (column 6) (value any) (group 6) (diagonal 0) (id 33)))
	(assert (possible (row 6) (column 4) (value any) (group 6) (diagonal 0) (id 34)))
	(assert (possible (row 6) (column 5) (value 6)   (group 6) (diagonal 0) (id 35)))
	(assert (possible (row 6) (column 6) (value any) (group 6) (diagonal 1) (id 36)))

)
