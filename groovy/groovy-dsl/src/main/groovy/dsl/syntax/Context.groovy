package dsl.syntax

import domain.Robot

interface Context {
    Robot getRobot()
}