/*
 * Copyright (C) 2021 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, {Component} from 'react';

interface EdgeListProps {
    onChange(edges: any): void;  // called when a new edge list is ready
    onDraw(edges: any): void; //called when we want to draw with the current list of edges
    onClear(edges: any): void; //called when we want to get rid of the current list of edges
    size: number; //size of the grid
    value: string; //display text
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps> {
    private edgeList: string;

    constructor(props: EdgeListProps) {
        super(props);
        this.edgeList = "";
    }

    onChangeInput = (evt: any) => {
        const edges = evt.target.value;
        this.edgeList = edges;
        this.props.onChange(edges);
    }

    handleDraw = () => {
        //first check if there is anything entered
        if(this.edgeList !== "") {
            let edge = this.edgeList.split("\n");
            for(let count = 0; count < edge.length; count++) {
                let line = edge[count];
                let edgeData = line.split(" ");
                if(edgeData[0] !== "" && edgeData.length !== 3) {
                    alert("You provided an incorrect format for the list of edges. You must input an edge in the form "+
                        "x1,y1 x2,y2 color where x1 and y1 are positive number coordinates for a point on the grid as "+
                        "well as x2 and y2 for another point.\nYou didn't give enough information for line" + count)
                }
                else {
                    if(edgeData[0] !== "") {
                        let first = edgeData[0].split(",");
                        let second = edgeData[1].split(",");
                        if(first.length !== 2 || second.length !== 2) {
                            alert("You provided an incorrect format for the list of edges. You must input an edge in " +
                                "the form x1,y1 x2,y2 color where x1 and y1 are positive number coordinates for a" +
                                "point on the grid as well as x2 and y2 for another point.\nYou didn't give correct" +
                                "point formatting for line" + count);
                        }
                        else {
                            let firstX = parseInt(first[0]);
                            let firstY = parseInt(first[1]);
                            let secondX = parseInt(second[0]);
                            let secondY = parseInt(second[1]);
                            if(isNaN(firstX) || isNaN(firstY) || isNaN(secondX) || isNaN(secondY) || firstX < 0 ||
                                firstY < 0 || secondX < 0 || secondY < 0 || firstX >= this.props.size ||
                                firstX >= this.props.size || secondX >= this.props.size || secondY >= this.props.size) {
                                alert("You provided an incorrect format for the list of edges. You must input an edge "+
                                    "in the form x1,y1 x2,y2 color where x1 and y1 are positive number coordinates " +
                                    "for a point on the grid as well as x2 and y2 for another point.\nYou didn't give "+
                                    "numbers or your numbers were invalid on line " + count);

                            }
                            else {
                                this.props.onDraw(this.edgeList);
                            }
                        }
                    }
                }
            }
        }
    }

    handleClear = () => {
        this.props.onDraw("");
    }

    render() {
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={this.onChangeInput}
                    value={this.props.value}
                /> <br/>
                <button onClick={this.handleDraw}>Draw</button>
                <button onClick={this.handleClear}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
