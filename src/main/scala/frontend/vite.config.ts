import { defineConfig } from "vite";
import tailwindcss from "@tailwindcss/vite";
import fullReload from "vite-plugin-full-reload";

export default defineConfig({
    plugins: [
        tailwindcss(),
        fullReload([
            "./common/**/*.scala",
            "./frontend/features/**/*.scala",
            "./main.css"
        ], {
            delay: 1000
        })
    ],
    server: {
      port: 5173,
      strictPort: true,
      cors: true
    },
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