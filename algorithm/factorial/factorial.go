package factorial


func Factorial( num int ) int {

	if num < 0 {
		return 0;
	}else if num != 1 {
		return num * Factorial( num - 1 )
	}else{
		return num
	}
}


func Factorial1(n int) int {
	if n <= 0 {
		return 1
	}
	return n * Factorial1(n-1)
}

// facItr returns n!.
func FacItr(n int) int {
	result := 1
	for n > 0 {
		result *= n
		n--
	}
	return result
}
