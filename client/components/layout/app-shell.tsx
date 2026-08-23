import {AvenIcon} from "../icons/aven-icon";
import { cn } from "@/lib/utils";
export function BrandMark({className} : {className?: string}) {
    return (
        <div className={cn(
            "flex items-center gap-2.5 font-semibold tracking-tight",
            className
        )}>
            <AvenIcon className="size-8 rounded-[10px]"/>
            <span className="font-heading text-[1.05rem] loading-none">Aven</span>
        </div>
    )
}