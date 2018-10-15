import {log} from "./lib/utils"

// Class definition is very concise. Out of many things it supports: constructor, inheritance and static methods.
class Unit {
    constructor(id, x, y) {
        this.id = id;
        this.move(x, y);
        this.visible = true;
    }

    move(x, y) {
        this.x = x;
        this.y = y;
    }

    toString() {
        return `Unit(${this.id})`;
    }

    static hidden(id) {
        return new DisabledUnit(id);
    }
}

log(new Unit(1, 10, 15));

// Class inheritance
class DisabledUnit extends Unit {
    constructor(id) {
        super(id, 0, 0);
        this.visible = false;
    }
}

log('> ' + Unit.hidden(12));
