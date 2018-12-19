import React from 'react'

const MessageInput = (props) => <input type="text" value={props.value}
                                       onChange={(event) => props.onChange(event.target.value)}/>;

export default MessageInput