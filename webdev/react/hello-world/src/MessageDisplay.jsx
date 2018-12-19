import React from "react"
import Spacer from './Spacer'
import './MessageDisplay.css'

export default class MessageDisplay extends React.PureComponent {

    constructor(props) {
        super(props);

        this.state = {
            active: !!props.active,
            indent: Math.random() < 0.5
        };

        setInterval(this.changeIndent.bind(this), 1000, this);
    }

    handleClick = (event) => {
        console.log(`changing component state`);
        this.setState((state) => ({active: !state.active}))
    };

    changeIndent(event) {
        this.setState((state) => ({indent: !state.indent}))
    }

    isIndented() {
        return this.state.indent && this.state.active && this.props.message;
    }

    render() {
        // Render two paragraphs with React JS API
        /*return [
            React.createElement("p", {class: 'hello'}, "Hello World!!!"),
            React.createElement("p", {class: 'hello active'}, "Hello World!!!")
        ]*/

        // Render two paragraphs wrapped in div element with React JS API
        /*return React.createElement("div", null,
                [
                    React.createElement("p", {class: 'hello'}, "Hello World!!!"),
                    React.createElement("p", {class: 'hello active'}, "Hello World!!!")
                ]
            )*/

        return (
            <div className={"hello " + (this.state.active ? 'active' : '')} onClick={this.handleClick}>
                {this.isIndented() && <Spacer/>}
                {this.props.message}
            </div>
        )
    }
}
