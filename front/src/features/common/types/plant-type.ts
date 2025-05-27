export type Plant = {
  id: number;
  common_name: string;
  scientific_name: string[];
  care_level: string;
  default_image: {
    original_url: string;
    medium_url: string;
  } | null;
  description: string;
};
