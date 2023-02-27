import React from 'react';
import { Link } from 'react-router-dom';
import {Button} from "semantic-ui-react";

export const  LandingPage= () =>  {
    return (
        <div className={"landingpage-container"}>
            <h1>Welcome to Chore-Tracker</h1>
            <Button><Link to="/api/login">Login</Link></Button>
        </div>
    );
}
