import { useState } from "react";
import { createCat } from "../api/Cat.graphql";

const CreateCat = () => {
    const [formData, setFormData] = useState({
        name: '',
        age: '',
        color: '',
        breed_id: '',
        origin_id: ''
    });

    const { createCatMutation } = createCat();

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setFormData(prevData => ({
            ...prevData,
            [name]: value
        }));
        console.log(formData)
    };

    const handleSubmit = async (e: any) => {
        e.preventDefault();

        try {
            const response = await createCatMutation({
                variables: {
                    name: formData.name,
                    age: parseInt(formData.age, 10),
                    color: formData.color,
                    breed_id: parseInt(formData.breed_id, 10),
                    origin_id: parseInt(formData.origin_id, 10),
                },
            });

            console.log('Cat added:', response.data);
            // Clear form data
            setFormData({
                name: '',
                age: '',
                color: '',
                breed_id: '',
                origin_id: ''
            });
        } catch (error) {
            console.error('Error adding cat:', error);
        }
    };


    return (
        <div>
            <h2>Add a Cat</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name:</label>
                    <input
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>Age:</label>
                    <input
                        type="number"
                        name="age"
                        value={formData.age}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>Color:</label>
                    <input
                        type="text"
                        name="color"
                        value={formData.color}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>Breed:</label>
                    <input
                        type="text"
                        name="breed_id"
                        value={formData.breed_id}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>Origin:</label>
                    <input
                        type="text"
                        name="origin_id"
                        value={formData.origin_id}
                        onChange={handleChange}
                    />
                </div>
                <button type="submit">Add Cat</button>
            </form>
        </div>
    )
}

export default CreateCat;