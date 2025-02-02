package main

type HomeTheaterFacade struct {
	amp   *Amplifier
	tuner *Tuner
}

func GetHomeTheaterInstance(
	amp *Amplifier,
	tuner *Tuner,
) *HomeTheaterFacade {
	h := &HomeTheaterFacade{}

	h.amp = amp
	h.tuner = tuner

	return h
}

func (h *HomeTheaterFacade) WatchMovie(movie string) {
	h.amp.On()
}

func (h *HomeTheaterFacade) EndMovie() {

	h.amp.Off()
}

func (h *HomeTheaterFacade) ListenToRadio(frequency float32) {
	h.tuner.On()
	h.tuner.SetFrequency(frequency)
	h.amp.On()
}
