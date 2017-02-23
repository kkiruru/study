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
