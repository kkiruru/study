package command

import "fmt"

type Command interface {
	Execute()
	Undo()
}

type SimpleRemoteControl struct {
	slot Command
}

func (s *SimpleRemoteControl) SetCommand(c Command) {
	s.slot = c
}

func (s *SimpleRemoteControl) ButtonWasPressed() {
	s.slot.Execute()
}

type RemoteControl struct {
	onCommands  [7]Command
	offCommands [7]Command
}

func GetRemoteControl() *RemoteControl {
	r := RemoteControl{}

	noCommand := NoCommand{}

	for i := 0; i < 7; i++ {
		r.onCommands[i] = &noCommand
		r.offCommands[i] = &noCommand
	}

	return &r
}

func (r *RemoteControl) SetCommand(slot int, onCommand Command, offCommand Command) {
	r.onCommands[slot] = onCommand
	r.offCommands[slot] = offCommand
}

func (r *RemoteControl) OnButtonWasPushed(slot int) {
	r.onCommands[slot].Execute()
}

func (r *RemoteControl) OffButtonWasPushed(slot int) {
	r.offCommands[slot].Execute()
}

type NoCommand struct{}

func (n *NoCommand) Execute() {
	fmt.Println("아무 기능이 없음")
}

func (n *NoCommand) Undo() {
	fmt.Println("되돌릴 상태가 없음")
}

type RemoteControlWithUndo struct {
	onCommands  [7]Command
	offCommands [7]Command
	undoCommand Command
}

func GetRemoteControlWithUndo() *RemoteControlWithUndo {
	r := RemoteControlWithUndo{}

	noCommand := NoCommand{}

	for i := 0; i < 7; i++ {
		r.onCommands[i] = &noCommand
		r.offCommands[i] = &noCommand
	}

	r.undoCommand = &noCommand

	return &r
}

func (r *RemoteControlWithUndo) SetCommand(slot int, onCommand Command, offCommand Command) {
	r.onCommands[slot] = onCommand
	r.offCommands[slot] = offCommand
}

func (r *RemoteControlWithUndo) OnButtonWasPushed(slot int) {
	r.onCommands[slot].Execute()
	r.undoCommand = r.onCommands[slot]
}

func (r *RemoteControlWithUndo) OffButtonWasPushed(slot int) {
	r.offCommands[slot].Execute()
	r.undoCommand = r.offCommands[slot]
}

func (r *RemoteControlWithUndo) UndoButtonWasPushed() {
	r.undoCommand.Undo()
}
