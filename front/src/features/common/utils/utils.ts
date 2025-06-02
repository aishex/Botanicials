export const formatDate = (date: Date) => {
  return new Intl.DateTimeFormat("pl-PL", {
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
  }).format(new Date(date));
};

export const truncateContent = (content: string, maxLength = 150) => {
  if (content.length <= maxLength) return content;
  return content.substring(0, maxLength) + "...";
};
