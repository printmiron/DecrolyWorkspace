import { PoroductInterface } from '../interface/poroduct-interface';

export const PRODUCTOS: PoroductInterface[] = [
    //asignamos tres productos y la monedas global para utilizalra despues 
    {
        nombre: 'iPhone 17',
        sku: 'ref: 23123123',
        precio: 1230
    },
    
    {
        nombre: 'Samsung Galaxy S25',
        sku: 'ref: 98765432',
        precio: 1150
    },

    {
        nombre: 'Xiaomi 15 Pro',
        sku: 'ref: 55566777',
        precio: 899
    }
];

export const CURRENCY = '€';
