package light

import "fmt"

type Light struct {
	Name string
}

func (l *Light) On() {
	fmt.Println(l.Name, "Light를 On한다")
}

func (l *Light) Off() {
	fmt.Println(l.Name, "Light를 Off한다")
}

type LightOnCommand struct {
	light *Light
}

func GetLightOnInstance(l *Light) *LightOnCommand {
	c := &LightOnCommand{light: l}
	return c
}

func (l *LightOnCommand) Execute() {
	l.light.On()
}

func (l *LightOnCommand) Undo() {
	fmt.Println("Undo>")
	l.light.Off()
}

type LightOffCommand struct {
	light *Light
}

func (l *LightOffCommand) Execute() {
	l.light.Off()
}

func (l *LightOffCommand) Undo() {
	fmt.Println("Undo>")
	l.light.On()
}

func GetLightOffInstance(l *Light) *LightOffCommand {
	c := &LightOffCommand{light: l}
	return c
}
