import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { editCat, getCatById } from '../api/Cat.graphql';

const EditCat = () => {
    const { cat_id } = useParams();
    const { loading, error, data } = getCatById(1);
    const { updateCatMutation } = editCat();

    const [formData, setFormData] = useState({
        name: '',
        age: '',
        color: '',
        breed_id: '',
        origin_id: ''
    });

    useEffect(() => {
        if (data && data.cat) {
            setFormData({
                name: data.cat.name,
                age: data.cat.age.toString(),
                color: data.cat.color,
                breed_id: data.cat.breed_id.toString(),
                origin_id: data.cat.origin_id.toString()
            });
        }
    }, [data]);

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setFormData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleSubmit = async (e: any) => {
        e.preventDefault();
        try {
            console.log("cat", cat_id)
            await updateCatMutation({
                variables: {
                    cat_id: parseInt(cat_id),
                    name: formData.name,
                    age: parseInt(formData.age, 10),
                    color: formData.color,
                    breed_id: parseInt(formData.breed_id, 10),
                    origin_id: parseInt(formData.origin_id, 10)
                }
            });
            console.log('Cat updated successfully.');
        } catch (err) {
            console.error('Error updating cat:', err);
        }
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    return (
        <div>
            <h2>Update Cat Details</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name:</label>
                    <input type="text" name="name" value={formData.name} onChange={handleChange} />
                </div>
                <div>
                    <label>Age:</label>
                    <input type="number" name="age" value={formData.age} onChange={handleChange} />
                </div>
                <div>
                    <label>Color:</label>
                    <input type="text" name="color" value={formData.color} onChange={handleChange} />
                </div>
                <div>
                    <label>Breed ID:</label>
                    <input type="number" name="breed_id" value={formData.breed_id} onChange={handleChange} />
                </div>
                <div>
                    <label>Origin ID:</label>
                    <input type="number" name="origin_id" value={formData.origin_id} onChange={handleChange} />
                </div>
                <button type="submit">Update Cat</button>
            </form>
        </div>
    );
};

export default EditCat;
