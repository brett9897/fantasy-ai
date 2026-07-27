import { defineConfig } from "vite";
import tailwindcss from "@tailwindcss/vite";

export default defineConfig({
    plugins: [tailwindcss()],
    build: {
        outDir: "../../resources/public/assets",
        emptyOutDir: true,
        rollupOptions: {
            input: "main.css",
            output: {
                assetFileNames: "app.css"
            }
        }
    }
});