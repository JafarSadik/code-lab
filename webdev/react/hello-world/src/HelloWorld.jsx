import React, {Component} from "react"
import './HelloWorld.css'

class HelloWorld extends Component {

    constructor(props) {
        super(props);

        this.state = { active: !!props.active };
        //this.handleClick = this.handleClick.bind(this);
    }

    handleClick(event) {
        console.log(`changing component state`);
        this.setState({active: !this.state.active})
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
            <div className={"hello " + (this.state.active ? 'active' : '')} onClick={this.handleClick.bind(this)}>
                {this.props.message}
            </div>
        )
    }
}

export default HelloWorld