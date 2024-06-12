import React, { useState } from 'react';
import {
    Modal,
    Box,
    Typography,
    TextField,
    Button,
    Paper
} from '@mui/material';
import api from '../axiosConfig';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    boxShadow: 24,
    p: 4,
};

function CreateCustomerModal({ open, onClose, onCustomerCreated }) {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [age, setAge] = useState('');

    const handleCreateCustomer = async () => {
        try {
            const response = await api.post('/api/customers', {
                name,
                email,
                age: parseInt(age, 10),
                accounts: []
            });
            onCustomerCreated(response.data);
            onClose();
        } catch (error) {
            console.error('Error creating customer:', error);
        }
    };

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Paper style={style}>
                <Typography id="modal-modal-title" variant="h6" component="h2">
                    Create New Customer
                </Typography>
                <Box component="form" noValidate autoComplete="off" sx={{ mt: 2 }}>
                    <TextField
                        fullWidth
                        label="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        margin="normal"
                    />
                    <TextField
                        fullWidth
                        label="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        margin="normal"
                    />
                    <TextField
                        fullWidth
                        label="Age"
                        value={age}
                        onChange={(e) => setAge(e.target.value)}
                        type="number"
                        margin="normal"
                    />
                    <Button
                        fullWidth
                        variant="contained"
                        color="primary"
                        onClick={handleCreateCustomer}
                        sx={{ mt: 2 }}
                    >
                        Create
                    </Button>
                </Box>
            </Paper>
        </Modal>
    );
}

export default CreateCustomerModal;
