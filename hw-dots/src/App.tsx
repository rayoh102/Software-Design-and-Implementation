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
import EdgeList from "./EdgeList";
import Grid from "./Grid";
import GridSizePicker from "./GridSizePicker";

// Allows us to write CSS styles inside App.css, any any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    gridSize: number;  // size of the grid to display
    edgeDisplay: string; //edges to be displayed
    edgeList: string; //edges to be processed
}

class App extends Component<{}, AppState> { // <- {} means no props.

    constructor(props: any) {
        super(props);
        this.state = {
            edgeList: "",
            edgeDisplay: "",
            gridSize: 4,
        };
    }

    updateEdgeList = (newEdges: string) => {
        this.setState( {
            edgeList: newEdges
        });
    }

    updateGridSize = (newSize: number) => {
        this.setState({
            gridSize: newSize
        });
    };

    clearEdgeList= (clear: string) => {
        this.setState( {
            edgeList: clear
        });
    }

    updateEdgeDisplay = (newEdgeDisplay: string) => {
        this.setState({
            edgeDisplay: newEdgeDisplay
        });
    }

    render() {
        const canvas_size = 500;
        return (
            <div>
                <p id="app-title">Connect the Dots!</p>
                <GridSizePicker value={this.state.gridSize.toString()} onChange={this.updateGridSize}/>
                <Grid size={this.state.gridSize} width={canvas_size} height={canvas_size} edges={this.state.edgeList}/>
                <EdgeList value = {this.state.edgeDisplay} onChange={this.updateEdgeDisplay} onDraw={this.updateEdgeList}
                          onClear={this.clearEdgeList} size={this.state.gridSize}/>
            </div>
        );
    }

}

export default App;

