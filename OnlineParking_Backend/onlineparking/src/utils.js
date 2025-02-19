import { config } from "./Services/Config"
export function createUrl(path) {
  return `${config.serverUrl}/${path}`
}