package main

import (
	"fmt"
)

type DVDPlayer struct {
	description  string
	currentTrack int
	amp          *Amplifier
	movie        string
}

func GetDvdPlayerInstance(description string, amp *Amplifier) *DVDPlayer {
	d := &DVDPlayer{}
	d.description = description
	d.amp = amp

	return d
}

func (d *DVDPlayer) On() {
	fmt.Println(d.description, " On")
}

func (d *DVDPlayer) Off() {
	fmt.Println(d.description, " Off")
}

func (d *DVDPlayer) Eject() {
	fmt.Println(d.description, " eject")
}

func (d *DVDPlayer) Play(movie string) {
	d.movie = movie
	d.currentTrack = 0
	fmt.Println(d.description, "playing \"", d.movie, "\"")
}

func (d *DVDPlayer) PlayWithTrack(track int) {
	if d.movie == "" {

	}
}
