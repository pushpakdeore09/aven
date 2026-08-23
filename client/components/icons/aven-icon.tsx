import React from 'react';
import { cn } from "@/lib/utils";

export const AvenIcon = ({ className, ...props }: React.ComponentProps<'svg'>) => {
  return (
    <svg
      xmlns="http://w3.org"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
      className={cn("text-primary animate-pulse", className)}
      {...props}
    >
      <path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z" />
      <polyline points="7.5 10.5 12 14 16.5 10.5" />
      <polyline points="7.5 6.5 12 10 16.5 6.5" />
    </svg>
  );
};

export default AvenIcon;
