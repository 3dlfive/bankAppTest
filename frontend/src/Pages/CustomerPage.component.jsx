import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import api from "../axiosConfig";
import { Container, Card, CardContent, Typography, List, ListItem, ListItemText, ListItemButton, Button, Box, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';

function CustomerPage() {
    const { id } = useParams();
    const [customer, setCustomer] = useState(null);
    const [open, setOpen] = useState(false);
    const [newAccount, setNewAccount] = useState({ currency: "USD", balance: 0.0 });

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await api.get(`/api/customers/${id}`);
                const data = await response.data;
                setCustomer(data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchData();
    }, [id]);

    const handleDeleteAccount = async (accountId) => {
        try {
            await api.delete(`/api/customers/${id}/accounts/${accountId}`);
            setCustomer((prevCustomer) => ({
                ...prevCustomer,
                accounts: prevCustomer.accounts.filter(account => account.id !== accountId)
            }));
        } catch (error) {
            console.error('Error deleting account:', error);
        }
    };

    const handleCreateAccount = async () => {
        try {
            const response = await api.post(`/api/customers/${id}/accounts`, newAccount);
            setCustomer((prevCustomer) => ({
                ...prevCustomer,
                accounts: [...prevCustomer.accounts, response.data]
            }));
            setOpen(false);
            setNewAccount({ currency: "USD", balance: 0.0 });
        } catch (error) {
            console.error('Error creating account:', error);
        }
    };

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewAccount(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    if (!customer) {
        return <div>Loading...</div>;
    }

    return (
        <Container>
            <Typography variant="h2" color="blue">Customer Details</Typography>
            <Card>
                <CardContent>
                    <Typography variant="body1">
                        ID: <span>{customer.id}</span>
                    </Typography>
                    <Typography variant="body1">
                        Name: <span>{customer.name}</span>
                    </Typography>
                    <Typography variant="body1">
                        Email: <span>{customer.email}</span>
                    </Typography>
                    <Typography variant="body1">
                        Age: <span>{customer.age}</span>
                    </Typography>
                    <Typography variant="h4" color="blue">
                        Accounts
                    </Typography>
                    <List>
                        {customer.accounts.map((account, index) => (
                            <ListItem key={index}>
                                <ListItemText
                                    primary={<Typography color="darkBlue">Number: {account.number}</Typography>}
                                    secondary={
                                        <>
                                            <Typography color="grey">Currency: {account.currency}</Typography>
                                            <Typography color="blue">Balance: {account.balance}</Typography>
                                        </>
                                    }
                                />
                                <Button
                                    variant="contained"
                                    color="secondary"
                                    onClick={() => handleDeleteAccount(account.id)}
                                    size="small"
                                    style={{ marginLeft: '10px' }}
                                >
                                    Delete
                                </Button>
                            </ListItem>
                        ))}
                    </List>
                    <Box mt={2}>
                        <Button variant="contained" color="primary" onClick={handleClickOpen}>
                            Create New Account
                        </Button>
                    </Box>
                </CardContent>
            </Card>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Create New Account</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        To create a new account, please enter the currency and initial balance here.
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        name="currency"
                        label="Currency"
                        type="text"
                        fullWidth
                        value={newAccount.currency}
                        onChange={handleInputChange}
                    />
                    <TextField
                        margin="dense"
                        name="balance"
                        label="Balance"
                        type="number"
                        fullWidth
                        value={newAccount.balance}
                        onChange={handleInputChange}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleCreateAccount} color="primary">
                        Create
                    </Button>
                </DialogActions>
            </Dialog>
        </Container>
    );
}

export default CustomerPage;
