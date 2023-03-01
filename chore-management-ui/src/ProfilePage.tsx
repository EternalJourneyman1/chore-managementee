import React from 'react';
import {Button, Header, Image, Segment} from 'semantic-ui-react';
import {useAuth} from "./AuthContext";
import './profilePage.css'

export const ProfilePage = ({onEditProfile}: any) => {
    const {user} = useAuth()

    return (
        <Segment className={'profile-container'}>
            <Image className={'profile-pic'} src={'./profile.jpg'} size="small" floated="right"/>
            <Header as="h1">Hello {user!.username || "Guest"}</Header>
            <Button color={'grey'} onClick={onEditProfile} disabled> Edit Profile</Button>
        </Segment>
    );
}
