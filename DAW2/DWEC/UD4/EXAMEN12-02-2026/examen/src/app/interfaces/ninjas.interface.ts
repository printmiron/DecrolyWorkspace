import { NinjastatsI } from "./ninjastats.interface";

export interface NinjasI {
    id: number;
    ninjaname: string;
    clan: string;
    fullname: string;
    image1: string;
    image2: string;
    gender: string;
    level: string;
    naturetype: string;
    affiliation: string;
    stats: NinjastatsI;

}
