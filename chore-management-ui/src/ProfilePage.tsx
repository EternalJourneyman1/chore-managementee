import React from 'react';
import {Button, Header, Segment} from 'semantic-ui-react';
import {useAuth} from "./AuthContext";

export const ProfilePage = ({ name, imageUrl, onEditProfile }: any) => {
    const {user} = useAuth()

    return (
        <Segment>
            {/*<Image src={'logo.svg'} size="medium" floated="right" />*/}
            <Header as="h1">Hello {user!.username || "Guest"}</Header>
            <Button color="yellow" onClick={onEditProfile}>Edit Profile</Button>
        </Segment>
    );
}
