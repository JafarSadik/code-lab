import React from 'react';
import './index.css';
import MessageDisplay from './MessageDisplay'
import MessageInput from './MessageInput'

export default class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {message: props.message || "???"}
    }

    updateMessage = (newMessage) => {
        this.setState({message: newMessage})
    };

    componentWillMount() {
        console.log(`componentWillMount`);
    }

    componentDidMount() {
        console.log(`componentDidMount`);
    }

    componentWillUnmount() {
        console.log(`componentWillUnmount`);
    }

    componentWillUpdate(nextProps, nextState, nextContext) {
        console.log(`componentWillUpdate`);
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log(`componentDidUpdate`);
    }

    componentDidCatch(error, errorInfo) {
        console.log(`componentDidCatch: ${error}`);
    }

    componentWillReceiveProps(nextProps, nextContext) {
        console.log(`componentWillReceiveProps`);
    }

    shouldComponentUpdate(nextProps, nextState, nextContext) {
        console.log(`shouldComponentUpdate`);
        return true;
    }

    render() {
        return <>
            <MessageDisplay active message={this.state.message}/>
            <MessageDisplay message={this.state.message}/>
            <MessageDisplay message={this.state.message}/>
            <MessageInput value={this.state.message} onChange={this.updateMessage}/>
        </>;
    }
}

