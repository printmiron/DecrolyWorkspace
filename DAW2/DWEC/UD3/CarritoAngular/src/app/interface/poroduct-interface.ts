export interface PoroductInterface {
    //repetimos la estructura del JSON para que recoge bien los datos
    currency: string;
    products: {
        title: string;
        price: number;
        sku: string;
    }[];

}
