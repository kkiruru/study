package main

func main() {
	a := GetAmplifierInstance("Top-O-Line Amplifier")
	t := GetTunerInstance("Top-O-Line AM/FM Tuner", a)
	h := GetHomeTheaterInstance(a, t)

	h.WatchMovie("Raiders of the Lost Ark")
	h.EndMovie()
}
