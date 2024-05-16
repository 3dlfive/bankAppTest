import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import api from "../axiosConfig";
import {Container, Card, CardContent, Typography, List, ListItem, ListItemText, ListItemButton} from '@mui/material';



function CustomerPage() {

    const { id } = useParams();
    const [customer, setCustomer] = useState(null);


    useEffect(() => {  const fetchData = async () => {
        try {
          const response = await api.get(`/api/customers/${id}`);


            const data = await response.data;
            setCustomer(data)
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }; fetchData();
    }, [id]);

    if (!customer) {
        return <div>Loading...</div>;
    }

    return (
        <Container>
            <Typography variant="h2" color="blue" >Customer Details</Typography >
            <Card>
                <CardContent>
                    <Typography variant="body1">
                        ID: <span>{customer.id}</span>
                    </Typography>
                    <Typography variant="body1">
                        Name: <span>{customer.name}</span>
                    </Typography>
                    <Typography variant="body1">
                        Email: <span >{customer.email}</span>
                    </Typography>
                    <Typography variant="body1">
                        Age: <span >{customer.age}</span>
                    </Typography>
                    <Typography variant="h4" color="blue" >
                        Accounts
                    </Typography>
                    <List>
                        {customer.accounts.map((account, index) => (
                            <ListItemButton key={index}>
                                <ListItemText
                                    primary={
                                        <Typography color="darkBlue">Number: {account.number}</Typography>
                                    }
                                    secondary={
                                        <>
                                            <Typography color="grey">Currency: {account.currency}</Typography>
                                            <Typography color="blue">Balance: {account.balance}</Typography>
                                        </>
                                    }
                                />
                            </ListItemButton>
                        ))}
                    </List>
                </CardContent>

            </Card>


        </Container>
    );
}

export default CustomerPage;
