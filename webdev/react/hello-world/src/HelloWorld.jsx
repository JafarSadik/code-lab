import React, {Component} from "react"
import './HelloWorld.css'

class HelloWorld extends Component {

    render() {
        // Render two paragraphs with React JS API
        /*return [
            React.createElement("p", {class: 'hello'}, "Hello World!!!"),
            React.createElement("p", {class: 'hello selected'}, "Hello World!!!")
        ]*/

        // Render two paragraphs wrapped in div element with React JS API
        /*return React.createElement("div", null,
                [
                    React.createElement("p", {class: 'hello'}, "Hello World!!!"),
                    React.createElement("p", {class: 'hello selected'}, "Hello World!!!")
                ]
            )*/

        return (
            <p class={"hello " + (this.props.selected ? 'selected' : '')}>
                {this.props.message}
            </p>
        )
    }
}

export default HelloWorld