package main

import "fmt"

func main() {

	arr := []int{3, 9, 4, 7, 5, 0, 1, 6, 8, 2}

	fmt.Println(arr)
	sort(arr)
	fmt.Println(arr)
}

func sort(arr []int) {
	quickSort(arr, 0, len(arr)-1)
}

func quickSort(arr []int, start int, end int) {
	var part2 = partition(arr, start, end)
	if start < part2-1 {
		quickSort(arr, start, part2-1)
	}

	if part2 < end {
		quickSort(arr, part2, end)
	}
}

func partition(arr []int, start int, end int) int {

	pivot := arr[(start+end)/2]

	for start <= end {
		for arr[start] < pivot {
			start++
		}

		for arr[end] > pivot {
			end--
		}

		if start <= end {
			swap(arr, start, end)
			start++
			end--
		}

	}

	return start
}

func swap(arr []int, start int, end int) {
	tmp := arr[start]
	arr[start] = arr[end]
	arr[end] = tmp
}
