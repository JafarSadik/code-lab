## Description

This is a simple console version of a drawing program. 
At this time, the functionality of the program is quite limited.  

In a nutshell, the program should works as follows:  
 1. Create a new canvas  
 2. Start drawing on the canvas by issuing various commands  
 3. Quit  


| Command         | Description
------------------|------------------------------------------------------------------------------
| C w h           | Create a new canvas of width w and height h.  
| L x1 y1 x2 y2   | Create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.  
| R x1 y1 x2 y2   | Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.  
| B x y c         | Fill the entire area connected to (x,y) with "colour" c. The behaviour of this is the same as that of the "bucket fill" tool in paint programs.  
| D x y           | Delete the shape or empty the area connected to (x, y)
| U               | Undo the last action on the current canvas
| Q               | Quit the program.  

