package main

import (
	"fmt"
	"reflect"

	"example.com/goinaction/reflact/target"
)

func main() {
	t := reflect.TypeOf(target.T{})

	t1, _ := t.FieldByName("T1")

	fmt.Println("T.T1 infomation")
	fmt.Println("Name", t1.Name)
	fmt.Println("Type", t1.Type)

	fmt.Println("========")
	t2, _ := t.FieldByName("t2")
	fmt.Println("T1.t2 infomation")
	fmt.Println("Name", t2.Name)
	fmt.Println("Type", t2.Type)

	fmt.Println("------------------------------")
	v := target.GetValue()

	t = reflect.TypeOf(v)

	t1, _ = t.FieldByName("T1")

	fmt.Println("T.T1 infomation")
	fmt.Println("Name", t1.Name)
	fmt.Println("Type", t1.Type)

	fmt.Println("========")
	t2, _ = t.FieldByName("t2")
	fmt.Println("T1.t2 infomation")
	fmt.Println("Name", t2.Name)
	fmt.Println("Type", t2.Type)

	//fmt.Println("t2 : ", v.t2) //v.t2 undefined (cannot refer to unexported field or method t2)
}
