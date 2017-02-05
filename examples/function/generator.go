package function

import (
	"fmt"
)

func NewIntGenerator() func() int {
	var next int
	return func() int {
		next++
		return next
	}
}

type VertexID int
type EdgeID int

func NewVertexIDGenerator() func() VertexID {
	var next int
	return func() VertexID {
		next++
		return VertexID(next)
	}
}

func NewEdgeIDGenerator() func() EdgeID {
	var next int
	return func() EdgeID {
		next++
		return EdgeID(next)
	}
}

func NewEdge(eid EdgeID) {
	fmt.Println(eid)
}
