package target

type T struct {
	T1 string
	t2 int64
}

func GetValue()(T) {
	t := T{ T1: "a", t2: 1}
	return t
}
