import { PowerstatsI } from "./powerstats.interface"

export interface HeroI {
    id: number;
    heroname: string;
    fullname: string;
    image1: string;
    image2: string;
    image3: string;
    gender: string;
    race: string;
    alignment: string;
    powerstats: PowerstatsI[];
}
