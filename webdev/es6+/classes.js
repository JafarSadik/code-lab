import {equal} from "assert"

// Class definition is very concise. Out of many things it supports: constructor, inheritance, getters/setters and static methods.
class Unit {

    constructor(id, x, y) {
        this.info = {id, x, y};
        this.move(x, y);
        this.visible = true;
    }

    move(x, y) {
        this.x = x;
        this.y = y;
    }

    // The get syntax binds an object property to a function that will be called when that property is looked up.
    get x() {
        return this.info.x;
    }

    get y() {
        return this.info.y;
    }

    get id() {
        return this.info.id;
    }

    // The set syntax binds an object property to a function to be called when there is an attempt to set that property.
    set x(x) {
        this.info.x = x;
    }

    set y(y) {
        this.info.y = y;
    }

    toString() {
        return `Unit(${this.id})`;
    }

    static hidden(id) {
        return new DisabledUnit(id);
    }
}

const unit = new Unit(1, 10, 15);
equal('Unit(1)', `${unit}`);
equal(1, unit.id);
equal(10, unit.x);
equal(15, unit.y);

// Class inheritance
class DisabledUnit extends Unit {
    constructor(id) {
        super(id, 0, 0);
        this.visible = false;
    }
}

const hiddenUnit = Unit.hidden(12);
equal('Unit(12)', `${hiddenUnit}`);
equal(12, hiddenUnit.id);
equal(0, hiddenUnit.x);
equal(0, hiddenUnit.y);
