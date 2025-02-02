package main

import (
	"fmt"

	"headfirst.design.patterns/command/ceilingfan"
	"headfirst.design.patterns/command/command"
	"headfirst.design.patterns/command/garagedoor"
	"headfirst.design.patterns/command/light"
)

func main() {
	fmt.Println("main")

	l := &light.Light{"거실"}
	lightOn := light.GetLightOnInstance(l)
	lightOff := light.GetLightOffInstance(l)

	simpleRemote := &command.SimpleRemoteControl{}

	simpleRemote.SetCommand(lightOn)
	simpleRemote.ButtonWasPressed()

	simpleRemote.SetCommand(lightOff)
	simpleRemote.ButtonWasPressed()

	g := &garagedoor.GarageDoor{}
	garageDoorOpen := garagedoor.GetGarageDoorOpenInstance(g)

	simpleRemote.SetCommand(garageDoorOpen)
	simpleRemote.ButtonWasPressed()

	remote := command.GetRemoteControl()
	remote.OnButtonWasPushed(0)
	remote.OffButtonWasPushed(0)

	livingRoomLight := &light.Light{"Living Room"}
	kitchenLight := &light.Light{"Kitchen"}

	livingRoomLightOn := light.GetLightOnInstance(livingRoomLight)
	livingRoomLightOff := light.GetLightOffInstance(livingRoomLight)
	remote.SetCommand(0, livingRoomLightOn, livingRoomLightOff)

	kitchenLightOn := light.GetLightOnInstance(kitchenLight)
	kitchenLightOff := light.GetLightOffInstance(kitchenLight)
	remote.SetCommand(1, kitchenLightOn, kitchenLightOff)

	remote.OnButtonWasPushed(0)
	remote.OffButtonWasPushed(0)
	remote.OnButtonWasPushed(1)
	remote.OffButtonWasPushed(1)

	cf := ceilingfan.GetCeilingFanInstance("Living Room")
	ceilingFanHigh := ceilingfan.GetCeilingFanHighCommandInstance(cf)
	ceilingFanOff := ceilingfan.GetCeilingFanOffCommandInstance(cf)

	remoteWithUndo := command.GetRemoteControlWithUndo()
	remoteWithUndo.SetCommand(0, livingRoomLightOn, livingRoomLightOff)
	remoteWithUndo.OnButtonWasPushed(0)
	remoteWithUndo.UndoButtonWasPushed()

	remoteWithUndo.OffButtonWasPushed(0)
	remoteWithUndo.UndoButtonWasPushed()

	remoteWithUndo.SetCommand(1, ceilingFanHigh, ceilingFanOff)
	remoteWithUndo.OnButtonWasPushed(1)
	remoteWithUndo.UndoButtonWasPushed()

	remoteWithUndo.OffButtonWasPushed(1)
	remoteWithUndo.UndoButtonWasPushed()
}
