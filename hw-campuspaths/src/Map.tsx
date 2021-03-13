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
import "./Map.css";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/Dropdown";

interface MapState {
    //Field keeping track of the short name for the starting building in our path
    start: string;
    //Field keeping track of the short name for the ending building in our path
    end: string;
    //Array of short building names to display as choices for navigation
    buildings: string[];
    //Image displayed as background (map of campus)
    backgroundImage: HTMLImageElement | null;
    //JSON string tracking the path between the given points
    path: string;
}

class Map extends Component<{} , MapState> {

    // NOTE:
    // This component is a suggestion for you to use, if you would like to.
    // It has some skeleton code that helps set up some of the more difficult parts
    // of getting <canvas> elements to display nicely with large images.
    //
    // If you don't want to use this component, you're free to delete it.

    canvas: React.RefObject<HTMLCanvasElement>;

    constructor(props: MapState) {
        super(props);
        this.state = {
            start: "",
            end: "",
            buildings: [],
            backgroundImage: null,
            path:"",
        };
        this.canvas = React.createRef();
    }

    componentDidMount() {
        this.fetchAndSaveImage();
        this.redraw();
    }

    componentDidUpdate() {
        this.redraw();
    }

    //On the home page, we retrieve a list of the available buildings from the server and set our buildings state equal
    //to the retrieved list. This allows us to populate our start/end selection buttons with the available choices.
    async getBuildings() {
        try {
            let responsePromise = fetch("http://localhost:4567/");
            let response = await responsePromise;
            let parsingPromise = response.json();
            let parsedObject = await parsingPromise;
            let buildings = parsedObject.buildings;
            let names = [];
            if(buildings.length > 0) {
                for(let building in buildings) {
                    names.push(buildings[building]["shortName"]);
                }
                this.setState( {
                    buildings: names,
                });
            }
        }
        catch (e) {
            alert("Server cannot be accessed at this time. Server may be down.");
        }
    }

    //Getting the background image on startup
    fetchAndSaveImage() {
        // Creates an Image object, and sets a callback function
        // for when the image is done loading (it might take a while).
        let background: HTMLImageElement = new Image();
        background.onload = () => {
            this.setState({
                backgroundImage: background
            });
        };
        // Once our callback is set up, we tell the image what file it should
        // load from. This also triggers the loading process.
        background.src = "./campus_map.jpg";
    }

    //When we have the correct number of parameters (a start and end location), we retrieve a path with the given
    //start and endpoints from the server, setting our path state equal to it.
    async drawPath() {
        let errorMsg = "";
        if(this.state.start === "") {
            errorMsg += "You must enter a start location\n"
            if(this.state.end === "") {
                errorMsg += "You must enter an end location"
            }
            alert(errorMsg);
        }
        else if(this.state.end === "") {
            errorMsg += "You must enter an end location"
            alert(errorMsg)
        }
        else {
            try {
                let responsePromise = fetch("http://localhost:4567/getPath?start=" + this.state.start + "&end=" +
                    this.state.end);
                let response = await responsePromise;
                let parsingPromise = response.json();
                let parsedObject = await parsingPromise;
                let path = parsedObject["path"];
                this.setState( {
                    path: path,
                });
            }
            catch (e) {
                alert("Server can't access path. Server may be down.")
            }
            if(this.canvas.current === null) {
                throw new Error("Unable to access canvas.")
            }
            let ctx = this.canvas.current.getContext("2d");
            if(ctx === null) {
                throw new Error("Unable to create canvas drawing context.")
            }
        }
    }

    //Resets our path, start, and end states. This will effectively reset the website to what it looked like on startup
    clear = () => {
        this.setState({
            path: "",
            start: "",
            end: "",
        });
    }

    //We call this method in our componentDidUpdate method, meaning when we change the path in drawPath, we call redraw.
    //Once redraw is called, we check that there is indeed a path to draw, and then draw it point by point.
    redraw = () => {
        let canvas = this.canvas.current;
        if (canvas === null) throw Error("Unable to draw, no canvas ref.");
        let ctx = canvas.getContext("2d");
        if (ctx === null) throw Error("Unable to draw, no valid graphics context.");
        //
        if (this.state.backgroundImage !== null) { // This means the image has been loaded.
            // Sets the internal "drawing space" of the canvas to have the correct size.
            // This helps the canvas not be blurry.
            canvas.width = this.state.backgroundImage.width;
            canvas.height = this.state.backgroundImage.height;
            ctx.drawImage(this.state.backgroundImage, 0, 0);
        }
        if(this.state.path.length > 0) {
            let path = this.state.path;
            ctx.strokeStyle = "red";
            ctx.lineWidth = 5;
            ctx.beginPath();
            for(let count = 0; count < path.length; count++) {
                let curPath = path[count];
                // @ts-ignore
                let start = curPath["start"];
                // @ts-ignore
                let end = curPath["end"];
                ctx.moveTo(start["x"], start["y"]);
                ctx.lineTo(end["x"], end["y"]);
                ctx.stroke();
            }
        }
    }

    //Sets the start building to the building specified
    handleStart = (eventKey:string) => {
        let index = parseInt(eventKey);
        this.setState( {
            start: this.state.buildings[index]
        });
    }

    //Sets the end building to the building specified
    handleEnd = (eventKey:string) => {
        let index = parseInt(eventKey);
        this.setState( {
            end: this.state.buildings[index]
        });
    }

    render() {
        this.getBuildings();
        //Populating the dropdown buttons with the building choices, associating a key to identify them by in the click
        //handler.
        let dropdownList = this.state.buildings.map((building, i) => {
            return (
                <Dropdown.Item key={i} eventKey={"" + i}>{building}</Dropdown.Item>
            )
        });
        let dest = "Path from:\n" + this.state.start + "\nto\n" + this.state.end;
        return (
            <div>
                <canvas ref={this.canvas}/>
                <div className="start_button">
                    <DropdownButton onSelect={this.handleStart}id="dropdown-basic-button" title="Choose Start Location">
                        {dropdownList}
                    </DropdownButton>
                </div>
                <div className="end_button">
                    <DropdownButton onSelect={this.handleEnd}id="dropdown-basic-button" title="Choose End Location">
                        {dropdownList}
                    </DropdownButton>
                </div>
                <div className="text_box">
                    <textarea
                        readOnly={true}
                        rows={5}
                        cols={30}
                        value={dest}
                    />
                </div>
                <div className="draw_button">
                    <button onClick={() => this.drawPath()}>Draw Path</button>
                </div>
                <div className="clear_button">
                    <button onClick={() => this.clear()}>Clear Path</button>
                </div>
            </div>
        )
    }
}

export default Map;