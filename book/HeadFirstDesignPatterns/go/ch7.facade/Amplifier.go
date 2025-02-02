package main

import (
	"fmt"
)

type Amplifier struct {
	description string
	tuner       *Tuner
	dvd         *DVDPlayer
	cd          *CDPlayer
}

func GetAmplifierInstance(description string) *Amplifier {
	a := &Amplifier{description: description}
	return a
}

func (a *Amplifier) On() {
	fmt.Println(a.description, " On")
}

func (a *Amplifier) Off() {
	fmt.Println(a.description, " Off")
}

func (a *Amplifier) SetStereoSound() {
	fmt.Println(a.description, " stereo mode on")
}

func (a *Amplifier) SetSurroundSound() {
	fmt.Println(a.description, " Surround sound mode on")
}

func (a *Amplifier) SetVolume(level int) {
	fmt.Println(a.description, " Setting volume to ", level)
}

func (a *Amplifier) SetTuner(tuner *Tuner) {
	fmt.Println(a.description+" Setting tuner to", tuner)
	a.tuner = tuner
}

func (a *Amplifier) SetDvd(dvd *DVDPlayer) {
	fmt.Println(a.description+" Setting DVD Player to", dvd)
	a.dvd = dvd
}

func (a *Amplifier) SetCd(cd *CDPlayer) {
	fmt.Println(a.description+" Setting CD Player to", cd)
	a.cd = cd
}
