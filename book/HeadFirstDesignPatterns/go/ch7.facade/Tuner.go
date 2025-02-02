package main

import "fmt"

type Tuner struct {
	description string
	amp         *Amplifier
	frequency   float32
}

func GetTunerInstance(description string, amp *Amplifier) *Tuner {
	t := &Tuner{description: description, amp: amp, frequency: 0}
	return t
}

func (t *Tuner) On() {
	fmt.Println(t.description + "On")
}

func (t *Tuner) Off() {
	fmt.Println(t.description, "Off")
}

func (t *Tuner) SetFrequency(frequency float32) {
	fmt.Println(t.description, " Setting Frequency to ", frequency)
	t.frequency = frequency
}

func (t *Tuner) SetAM() {
	fmt.Println(t.description + " Setting AM Mode")
}

func (t *Tuner) SetFM() {
	fmt.Println(t.description + " Setting FM Mode")
}
