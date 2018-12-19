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

    render() {
        return <>
            <MessageDisplay active message={this.state.message}/>
            <MessageDisplay message={this.state.message}/>
            <MessageDisplay message={this.state.message}/>
            <MessageInput value={this.state.message} onChange={this.updateMessage}/>
        </>;
    }
}

