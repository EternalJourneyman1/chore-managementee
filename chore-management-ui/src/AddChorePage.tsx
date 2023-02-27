import {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import './addChorePage.css'

import {Button, Dropdown, Form, Input, Label} from 'semantic-ui-react';

const LocationOptions = [
    {key: 'Living Room', text: 'Living Room', value: 'Living Room'},
    {key: 'Kitchen', text: 'Kitchen', value: 'Kitchen'},
    {key: 'Hallway', text: 'Hallway', value: 'Hallway'},
    {
        key: 'Downstairs Bathroom',
        text: 'Downstairs Bathroom',
        value: 'Downstairs Bathroom',
    },
    {
        key: 'Upstairs Bathroom',
        text: 'Upstairs Bathroom',
        value: 'Upstairs Bathroom',
    },
    {key: 'Other', text: 'Other', value: 'Other'},
];

export const AddChorePage = () => {
    const navigate = useNavigate();
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const [images, setImages] = useState<any[]>([]);

    const handleNameChange = (e: any) => {
        setName(e.target.value);
    };

    const handleLocationChange = (e: any) => {
        setLocation(e.target.textContent);
    };

    const handleImageChange = (e: { target: { files: any; }; }) => {
        setImages([...images, ...e.target.files]);
    };

    const handleImageRemove = (index: number) => {
        const newImages = [...images];
        newImages.splice(index, 1);
        setImages(newImages);
    };


    const handleSubmit = (e: any) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('name', name);
        formData.append('location', location);
        if (images.length > 0) {

            images?.forEach((image) => {
                formData.append('images', image);
            });
        }
        fetch('http://localhost:8080/api/chores/done', {
            method: 'POST',
            credentials: 'include',
            body: formData,
        })
            .then((response) => {
                console.log(formData)
                console.table(formData)
                console.table(response)
                if (response.ok) {
                    navigate('/profile')
                }
            })
            .catch((error) => console.error(error));
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Field>
                <Label>Name</Label>
                <Input placeholder='Name' value={name} onChange={handleNameChange}/>
            </Form.Field>
            <Form.Field>
                <Label>Location</Label>
                <Dropdown
                    placeholder='Click on Location'
                    fluid
                    selection
                    options={LocationOptions}
                    value={location}
                    onChange={handleLocationChange}
                    onClick={handleLocationChange}
                />
            </Form.Field>
            <Form.Field>
                <Label>Images</Label>
                <input type='file' multiple onChange={handleImageChange}/>
                {images.map((image, index) => (
                    <div className='image-container' key={index}>
                        <img src={URL.createObjectURL(image)} alt={image.name}/>
                        <Button icon='trash' onClick={() => handleImageRemove(index)}/>
                    </div>
                ))}
            </Form.Field>
            <Button type='submit'>Submit</Button>
        </Form>
    );
};
